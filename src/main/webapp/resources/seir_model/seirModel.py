import json
from urllib.request import urlopen
from numpy import array as vector
from flask import Flask

import scipy.integrate
import numpy
import matplotlib.pyplot as plt
from urllib.request import urlopen

import re
import requests
from bs4 import BeautifulSoup

#우리나라 코로나SEIR 모델

covidData = [];

def getSEIRCoronaData():
    response = urlopen("https://api.corona-19.kr/korea/?serviceKey=eUPai5pXJsw4o8dIYvChuOKyNHcgjRWST").read().decode('utf-8')
    responseJson = json.loads(response)
    covidData.append(responseJson.get("checkingCounter").replace(",",""))
    covidData.append(responseJson.get("TotalCase").replace(",",""))
    covidData.append(responseJson.get("TotalRecovered").replace(",",""))
    covidData.append(responseJson.get("city1n").replace(",",""))
    covidData.append(responseJson.get("city2n").replace(",",""))
    covidData.append(responseJson.get("city3n").replace(",",""))
    covidData.append(responseJson.get("city4n").replace(",",""))
    covidData.append(responseJson.get("city5n").replace(",",""))
    print("검사중 : " + covidData[0] + " /확진자 : " + covidData[1] + " /격리해제자 : " + covidData[2])  
    print(covidData)
       
# Explicit Euler method
def euler_method(f,t0,x0,t1,h):
    t = t0; x = x0
    a = [[t,x]]
    for k in range(0,1+int((t1-t0)/h)):
        t = t0 + k*h
        x = x + h*f(t,x)
        a.append([t,x])
    return a

def SEIR_model(beta,gamma,a):
    def f(t,x):
        S,E,I,R = x
        return vector([
            -beta*S*I,
            beta*S*I - a*E,
            a*E - gamma*I,
            gamma*I
        ])
    return f

def SEIR_simulation(beta,gamma,a,E0,I0,days,step=0.1):
    x0 = vector([1.0-E0-I0,E0,I0,0.0])
    return euler_method(SEIR_model(beta,gamma,a),0,x0,days,step)

def diagram(simulation):
    plt.style.use('fivethirtyeight')
    figure,axes = plt.subplots()
    figure.subplots_adjust(bottom = 0.15)
    axes.grid(linestyle = ':', linewidth = 2.0, color = "#808080")
    t,x = zip(*simulation())
    S,E,I,R = zip(*x)
    axes.plt(t,S, color = "#0000cc")
    axes.plt(t,E, color = "#ffb000", linestyle = '--')
    axes.plt(t,I, color = "#a00060")
    axes.plt(t,R, color = "#008000", linestyle = '--')
    #plt.show()
    plt.savefig('./sin.png')    
    
def simulation1():
    covidData.clear()
    getSEIRCoronaData()
    N = 51820000 # 우리나라 인구수
    R0 = 3; gamma = 0.07 # 코로나 R0 추정치 2.2~3.3 중 3 사용, 평균잠복기 1 / 14일 = 0.07
    eTmp = int(covidData[0])
    iTmp = int(covidData[1])
    return SEIR_simulation(
        beta = R0*gamma, gamma = gamma, a = 1/5.5, #평균지연시간
        E0 = eTmp/N, I0 = iTmp/N, days = 230)


#여기부터 SIR모델 (현재 일본 코로나 종식일 구현)        
japanCovidData = [];

def getSIRCoronaData():
	
    url = 'https://www.okinawaobaksa.com/corona19/'

    response = requests.get(url)

    if response.status_code == 200:
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
        I0Data = str(soup.select_one('#counters > div:nth-child(1) > div > span'))
        R0Data = str(soup.select_one('#counters > div:nth-child(3) > div > span'))

        I0num = re.findall("\d+", I0Data)
        R0num = re.findall("\d+", R0Data)

        japanCovidData.append(I0num[0])
        japanCovidData.append(R0num[0])
    else : 
        print(response.status_code)
        
def SIR_model(y, t, beta, gamma):
    S, I, R = y
    
    dS_dt = -beta * S * I
    dI_dt = beta * S * I - gamma * I
    dR_dt = gamma * I
    
    return([dS_dt, dI_dt, dR_dt])

#Initial conditions
def SIR_diagram():
    getSIRCoronaData()
    I0 = int(japanCovidData[0])
    R0 = int(japanCovidData[1])
    S0 = 126050796 - I0 - R0 #네이버 일본 인구수 기준 126050796명
    
    N = S0 + I0 + R0
    
    S0 /= N
    I0 /= N
    R0 /= N
    beta = 0.1934
    gamma = 1/14
    
    #Time vector
    t = numpy.linspace(0, 100, 10000)
    
    #Result
    solution = scipy.integrate.odeint(SIR_model, [S0, I0, R0], t, args=(beta, gamma))
    solution = numpy.array(solution)
    
    #Plot result
    plt.figure(figsize = [6, 4])
    plt.plot(t, solution[:, 0], label="S(t)")
    plt.plot(t, solution[:, 1], label="I(t)")
    plt.plot(t, solution[:, 2], label="R(t)")
    plt.grid()
    plt.legend()
    plt.xlabel("Time")
    plt.ylabel("Proportions")
    plt.title("Japan SIR model")
    plt.savefig('./japan_sir.png') 
    
    
app = Flask (__name__)
 
@app.route('/seir')
def runSEIRServer():
    diagram(simulation1)
    valueStr = ""
    for i in covidData:
        valueStr += i + " "
    return valueStr
 
@app.route('/sir_japan')
def runSIRServer():
    SIR_diagram()
    return "SIR Server Run..."
        
if __name__ == "__main__":
    app.run()
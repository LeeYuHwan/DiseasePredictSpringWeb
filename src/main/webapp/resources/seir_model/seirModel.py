import json
from urllib.request import urlopen
from numpy import array as vector
from flask import Flask

import scipy.integrate
import numpy
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
    covidData.append(responseJson.get("TotalDeath").replace(",",""))
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
    import matplotlib.pyplot as plot
    plot.style.use('fivethirtyeight')
    figure,axes = plot.subplots()
    figure.subplots_adjust(bottom = 0.15)
    axes.grid(linestyle = ':', linewidth = 2.0, color = "#808080")
    t,x = zip(*simulation())
    S,E,I,R = zip(*x)
    axes.plot(t,S, color = "#0000cc")
    axes.plot(t,E, color = "#ffb000", linestyle = '--')
    axes.plot(t,I, color = "#a00060")
    axes.plot(t,R, color = "#008000", linestyle = '--')
    #plot.show()
    plot.savefig('./sin.png')    
    
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
SIRCovidData = [];

def getSIRCoronaData(sw):
	
    url = ""
    I0Data = ""
    R0Data = ""
    death = ""
    if sw == "us":
        url = "https://coronaboard.com/"    
    else:
        url = 'https://www.okinawaobaksa.com/corona19/'
    

    response = requests.get(url)
    #responseJson.get("checkingCounter").replace(",","")
    
    if response.status_code == 200 and sw == "japan":
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
        I0Data = str(soup.select_one('#counters > div:nth-child(1) > div > span'))
        R0Data = str(soup.select_one('#counters > div:nth-child(3) > div > span'))

        I0num = re.findall("\d+", I0Data)
        R0num = re.findall("\d+", R0Data)
        
        death = str(soup.select_one('#counters > div:nth-child(2) > div > span'))
        death = re.findall("\d+", death)
        
        SIRCovidData.append(I0num[0])
        SIRCovidData.append(R0num[0])
        SIRCovidData.append(death[0])
    elif response.status_code == 200 and sw == "us":
     
        url = 'https://www.worldometers.info/coronavirus/country/us/'
        response = requests.get(url)
        
        soup = BeautifulSoup(response.text, "html.parser").find_all("div","maincounter-number")
        
        tmpData = []
        for i in enumerate(soup[0:3]):
            tmpData.append(i[1].find('span').get_text())
                     
        SIRCovidData.append(tmpData[0].replace(",","").rstrip())
        SIRCovidData.append(tmpData[2].replace(",",""))
        SIRCovidData.append(tmpData[1].replace(",",""))
        '''from selenium import webdriver
        options = webdriver.ChromeOptions()
        options.add_argument('headless')
        
        driver = webdriver.Chrome('C:\chromedriver_win32\chromedriver', chrome_options=options)
        driver.implicitly_wait(10)
        driver.get(url)
        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')
        html = response.text
        soup = BeautifulSoup(html, 'html.parser')
        I0Data = str(soup.select_one('#top > div.top.container > div.row.dashboard.domestic.justify-content-around > div:nth-child(1) > p.confirmed.number').text)
        R0Data = str(soup.select_one('#top > div.top.container > div.row.dashboard.domestic.justify-content-around > div:nth-child(3) > p.released.number').text)
        death = str(soup.select_one('#top > div.top.container > div.row.dashboard.domestic.justify-content-around > div:nth-child(2) > p.death.red.number').text)'''
               
    else : 
        print(response.status_code)
        
def SIR_model(y, t, beta, gamma):
    S, I, R = y
    
    dS_dt = -beta * S * I
    dI_dt = beta * S * I - gamma * I
    dR_dt = gamma * I
    
    return([dS_dt, dI_dt, dR_dt])

#Initial conditions
def SIR_diagram(sw):
    import matplotlib.pyplot as plt
    getSIRCoronaData(sw)
    I0 = int(SIRCovidData[0])
    R0 = int(SIRCovidData[1])
    S0 = 0
    if sw == "japan":
        S0 = 126050796 - I0 - R0 #네이버 일본 인구수 기준 126050796명
    elif sw == "us":
        S0 = 330612170 - I0 - R0 #네이버 미국 인구수 기준 330612170명
       
    N = S0 + I0 + R0
    
    S0 /= N
    I0 /= N
    R0 /= N
    beta = 0.1934
    gamma = 1/14
    
    #Time vector
    t = numpy.linspace(0, 200, 10000)
    
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
    
    if sw == "japan":
        plt.title("Japan SIR model")
        plt.savefig('./japan_sir.png')
    elif sw == "us":
        plt.title("America SIR model")
        plt.savefig('./us_sir.png')
           
app = Flask (__name__)
 
@app.route('/seir')
def runSEIRServer():
    diagram(simulation1)
    valueStr = ""
    for i in covidData:
        valueStr += i + " "
    return valueStr
 
@app.route('/sir_japan')
def runSIRJpanServer():
    SIRCovidData.clear()
    SIR_diagram("japan")
    valueStr = ""
    for i in SIRCovidData:
        valueStr += i + " "
    return valueStr

@app.route('/sir_us')
def runSIRUSServer():
    SIRCovidData.clear()
    SIR_diagram("us")
    valueStr = ""
    for i in SIRCovidData:
        valueStr += i + " "
    return valueStr
        
if __name__ == "__main__":
    app.run()
import json
from urllib.request import urlopen
from numpy import array as vector
from flask import Flask

def getCoronaData(value):
	response = urlopen("https://api.corona-19.kr/korea/?serviceKey=eUPai5pXJsw4o8dIYvChuOKyNHcgjRWST").read().decode('utf-8')
	responseJson = json.loads(response)
	return responseJson.get(value)

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
    N = 51820000 # 우리나라 인구수
    R0 = 3; gamma = 0.07 # 코로나 R0 추정치 2.2~3.3 중 3 사용, 평균잠복기 1 / 14일 = 0.07
    eTmp = int(getCoronaData("checkingCounter").replace(",",""))
    iTmp = int(getCoronaData("TotalCase").replace(",",""))
    return SEIR_simulation(
        beta = R0*gamma, gamma = gamma, a = 1/5.5, #평균지연시간
        E0 = eTmp/N, I0 = iTmp/N, days = 230)

app = Flask (__name__)
 
@app.route('/seir')
def hello_world():
    diagram(simulation1) 
    return 'seirServerRun'
 
if __name__ == "__main__":
    app.run()



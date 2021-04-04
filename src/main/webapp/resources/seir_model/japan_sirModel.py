import scipy.integrate
import numpy
import matplotlib.pyplot as plt
import json
from urllib.request import urlopen

import re
import requests
from bs4 import BeautifulSoup

japanCovidData = [];

def getCoronaData():
	
    japanUrl = 'https://www.okinawaobaksa.com/corona19/'

    response = requests.get(japanUrl)

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
getCoronaData()
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
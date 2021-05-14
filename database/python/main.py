import json
import time
import tracemalloc
"""

startMerge=time.time()
f = open('bazaedh8000.json',encoding="utf8")
data = json.load(f)
datalist=list(data['items'])

f2=open('bazaedh.json',encoding="utf8")
data2=json.load(f2)
datalist2=list(data2['items'])

f3=open('bazaedgGR.json',encoding="utf8")
data3=json.load(f3)
datalist3=list(data3['items'])
finaldata=datalist+datalist2+datalist3
stopMege=time.time()
print("Merge time: ",stopMege-startMerge)



dicts=[]
for i in range(0,50):
   dicts+=finaldata


with open('bazaBIG.json', 'w',encoding="utf8") as outfile:
   json.dump(dicts, outfile)
"""


tracemalloc.start()
loadstart=time.time()
f = open('bazaBIG.json',encoding="utf8")
data = json.load(f)
datalist=list(data)

loadend=time.time()
loadFinal=loadend-loadstart
print("Loading database time: ",loadFinal)



"""
iter=0
searchStart=time.time()
for i in datalist:
   if 'Caesar' in i['transcription']:
      iter+=1

searchEnd=time.time()
searchFinal=searchEnd-searchStart
print("Searching time 1: ",searchFinal)

iter2=0
searchStart2=time.time()
for i in datalist:
   if 'people' in i:
      for j in i['people']:
         if('gender' in j):
            if (j['gender']=='female'):
               iter2+=1

searchEnd2=time.time()
searchFinal2=searchEnd2-searchStart2
print("Searching time2: ",searchFinal2)
"""



iter3=0
searchStart3=time.time()
for i in datalist:
   if 'material' in i:
      if 'marble' in i['material']:
         if 'not_before' in i:
            if int(200)>=int(i['not_before']):
               iter3+=1

searchEnd3=time.time()
searchFinal3=searchEnd3-searchStart3
print("Searching time3: ",searchFinal3)
"""

iter4=0
searchStart4=time.time()
for i in datalist:
   if 'language' in i:
      if 'Greek' in i['language']:
         if 'Italy' in i['country']:
            iter4+=1
searchEnd4=time.time()
searchFinal4=searchEnd4-searchStart4
print("Searching time4: ",searchFinal4)

iter5=0
searchStart5=time.time()
for i in datalist:
   if 'findspot' in i:
      if 'Roma' in i['findspot']:
         if 'Caesar' in i['transcription']:
            if 'not_before' in i:
               if int(200) >= int(i['not_before']):
                  iter5+=1

searchEnd5=time.time()
searchFinal5=searchEnd5-searchStart5
print("Searching time5: ",searchFinal5)

iter6=0
searchStart6=time.time()
for i in datalist:
   if 'language' in i:
      if 'Greek' in i['language']:
         if 'material' in i:
            if 'marble' in i['material']:
               if 'people' in i:
                  for j in i['people']:
                     if ('gender' in j):
                        if (j['gender'] == 'female'):
                           iter6+=1

searchEnd6=time.time()
searchFinal6=searchEnd6-searchStart6
print("Searching time6: ",searchFinal6)
"""

current, peak = tracemalloc.get_traced_memory()
print(f"Current memory usage is {current / 10 ** 6}MB; Peak was {peak / 10 ** 6}MB")
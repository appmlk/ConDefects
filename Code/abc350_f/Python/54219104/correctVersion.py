from collections import deque 
from collections import defaultdict
import sys
sys.setrecursionlimit(1000000)
s=input()
t=[]
for i in s:
    t.append(i)

k=0
for i in range(len(t)):
    if t[i]=="(":
        k+=1
    elif t[i]==")":
        k-=1
    else:
        if k%2==1:
            if ord(t[i])-ord("A")<26:
                t[i]=chr(ord(t[i])-ord("A")+ord("a"))
            else:
                t[i]=chr(ord(t[i])+ord("A")-ord("a"))
#print(t)
pi=defaultdict(int)
ppi=defaultdict(int)
kakko=[0 for i in range(len(t))]
#kk=0
qu=[]
for i in range(len(t)):
    if t[i]=="(":
        
        #kk+=1
        qu.append(i)
        #pi[kk]=i
        #kakko[i]=kk
    elif t[i]==")":
        r=qu.pop(-1)
        ppi[i]=r
        ppi[r]=i
#print(ppi)
        #kakko[i]=-qu.pop(-1)
#print(kakko)
def kai(s,f,u):
    #print("p",s,f,u,"p")
    #if s==f:
        #return
    if u==0:
        i=s
        while s<=i<f:
        #for i in range(s,f):
            if t[i]=="(":
                p=ppi[i]
                #p=kakko.index(-kakko[i])
                kai(i,p,1)
                i=p+1
            elif t[i]==")":
                break
            else:
                print(t[i],end="")
                i+=1
    else:
        i=f-1
        while s<=i<f:
        #for i in reversed(range(s,f)):
            if t[i]==")":
                p=ppi[i]
                #p=kakko.index(-kakko[i])
                kai(p+1,i,0)
                i=p-1
            elif t[i]=="(":
                break
            else:
                print(t[i],end="")
                i-=1

kai(0,len(t),0)
print()


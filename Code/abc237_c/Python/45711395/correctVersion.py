S=input()
N=len(S)
if S=="a"*N:
    print("Yes")
    exit()
    
a=0
b=0
s=-1
t=-1
for i in range(N-1,-1,-1):
    if S[i]=="a":
        a+=1
    else:
        s=i
        break
        
for i in range(N):
    if S[i]=="a":
        b+=1
    else:
        t=i
        break
        
if a<b:
    print("No")
    exit()
    
for i in range(t,s+1):
    if S[i]!=S[s+t-i]:
        print("No")
        exit()
        
print("Yes")
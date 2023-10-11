S=input()
T=input()
cnt=set()
N=len(S)
for i in range(N):
    cnt.add(ord(S[i])-ord(T[i]))
    
if len(cnt)==1:
    print("Yes")
else:
    print("No")
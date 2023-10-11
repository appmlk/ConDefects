t=int(input())
for i in range(t):
    n, k = map(int, input().split())
    s=list(input())
    b1 = s.count("1")
    tz = [0]
    tq=[0]
    cz=0
    cq=0
    ac=0
    for j in range(n):
        cz+=int(s[j]=="0")
        tz.append(cz)
        cq+=int(s[j]=="?")
        tq.append(cq)
    for j in range(0,n-k):
        if tz[j+k]-tz[j]==0 and tq[j+k]-tq[j]==k-b1 :
            ac+=1
    if ac==1:
        print("Yes")
    else:
        print("No")
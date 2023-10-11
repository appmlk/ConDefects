N,M=map(int, input().split())

A = list(map(int, input().split()))
l = [set([]) for i in range(M)]
for i,a in enumerate(A,1):
    cnt = max(1,(-a+i-1)//i)
    a += i * cnt
    #print(cnt)
    for j in range(cnt,M+1):
        l[j-1].add(a)
        a += i
        if a>=200000:
            break
#print(l)
for i in range(M):
    for j in range(200000):
        if j not in l[i]:
            print(j)
            break

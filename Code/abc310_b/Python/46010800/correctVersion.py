n , m = map(int,input().split())
p = [None] * n
#c = [None] * n
f = [None] * n
#e1 = {}
for i in range(n):
    e = list(map(int,input().split()))
    p[i] = e[0]
#    c[i] = e[1]
    f[i] = set(e[2:])
for i in range(n):
    for j in range(n):
        if p[i] >= p[j] and f[j].issuperset(f[i]) and (p[i] > p[j] or len(f[j]) > len(f[i])):
#            print("i=",i+1,"j=",j+1,f[j],"<=",f[i])
            print("Yes")
            exit()  
#    e1[p[i]] = f[i]
print("No")

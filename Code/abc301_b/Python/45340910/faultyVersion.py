n = int(input())
a = list(map(int,input().split()))

b = []
for i in range(n-1):
    if a[i] <= a[i+1]:
        b += list(range(a[i],a[i+1]))
    if a[i] >= a[i+1]:
        b += list(range(a[i],a[i+1],-1))
        
b.append(a[-1])

print(b)
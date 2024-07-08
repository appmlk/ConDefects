t = int(input())

ins = [(1,1)]

for x in range(2,19,2):
    v = pow(10,x)
    h = pow(10,x//2)
    ins.append((v-h*2,v-h*2))
    ins.append((v-h,v+h-1))
print(ins)
for _ in range(t):
    n = int(input())
    ans = 0
    for l,r in ins:
        r = min(r,n)
        if l <= r:
            ans += r-l+1
    print(ans)

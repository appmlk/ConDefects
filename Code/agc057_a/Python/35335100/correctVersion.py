T = int(input())
for _ in range(T):
    l,r = input().split()
    l = int(l)
    if r=="1":
        print(1)
    else:
        if r[0]=="1":
            a = int(r[:-1])
            b = int(r[1:])
            r0 = max(a,b)+1
        else:
            r0 = int("1"+"0"*(len(r)-1))
        r = int(r)
        if l<=r0:
            print(r-r0+1)
        else:
            print(r-l+1)
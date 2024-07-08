a, b = map(int, input().split())
md = 998244353
def inv(num):
    mo=998244353
    return pow(num, mo-2, mo)
def factorization(n):
    arr = []
    temp = n
    for i in range(2, int(-(-n**0.5//1))+1):
        if temp%i==0:
            cnt=0
            while temp%i==0:
                cnt+=1
                temp //= i
            arr.append([i, cnt])

    if temp!=1:
        arr.append([temp, 1])

    if arr==[]:
        arr.append([n, 1])

    return arr
bun=factorization(a)
ans=1
for i,j in bun:
    ans=ans*(j*b+1)%md
if int(a**0.5)*int(a**0.5)==a and b%2==1:
    print((ans*b-1)*inv(2)%md)
else:
    print(ans*b*inv(2)%md)
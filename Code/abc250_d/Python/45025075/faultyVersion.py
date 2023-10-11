def enum_primes(n):
    prime_flag = [1] * (n + 1)
    prime_flag[0] = 0
    prime_flag[1] = 0
    i = 2
    while i * i <= n:
        if prime_flag[i]:
            for j in range(2 * i, n + 1, i):
                prime_flag[j] = 0
        i += 1
    return [i for i in range(n + 1) if prime_flag[i]]

N=int(input())
n=int(N**(1/3))+1
li=enum_primes(n)

ans=0
for i in range(len(li)):
    p=li[i]
    ok=i
    ng=len(li)-1
    while ng-ok>1:
        j=(ok+ng)//2
        q=li[j]
        if  p*q**3<=N:
            ok=j
        else:
            ng=j
    if ok>i:
        ans+=ok-i

print(ans)
#2 3 3 4
#素数列挙
def prime(N):
    primes = []
    for i in range(2, N + 1):
        primes.append(i)
        for p in range(2, i):
            if i % p == 0:
                primes.remove(i)
                break
    return primes
setx=set(prime(200))

A,B,C,D=map(int,input().split())
for i in range(A,B+1):
    q=True
    for j in range(C,D+1):
        num=i+j
        if num in setx:
            q=False
            break
    if q==True:
        print("Takahashi")
        exit()
print("Aoki")
        
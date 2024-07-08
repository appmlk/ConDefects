primes=[]
for i in range(2,101):
    for j in range(2,i):
        if i%j==0:
            break
    else:
        primes.append(i)

a,b,c,d=map(int,input().split())

for i in range(a,b+1):
    for j in range(c,d+1):
        if i+j in primes:
            break
        if j==d:
            print('Takahashi')
            exit()
print('Aoki')
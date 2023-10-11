A,B=map(int,input().split())
now=min(A,B)
x=abs(A-B)
ans=0
if A==B:
    print(A)
    exit()
def make_divisors(n):
    lower_divisors , upper_divisors = [], []
    i = 1
    while i*i <= n:
        if n % i == 0:
            lower_divisors.append(i)
            if i != n // i:
                upper_divisors.append(n//i)
        i += 1
    return lower_divisors + upper_divisors[::-1]
l= make_divisors(x)
substract=1
for choice in l:
    if now%choice==0:
        substract=choice
l=l[::-1]

while True:

    t_sub= now
    for choice in l:
        if choice%substract==0 and choice!=substract:
     
            if now%choice<t_sub:
                t_sub=now%choice
                n_substract= choice
    ans+=t_sub//substract

    if now==t_sub:
        break
    now -= t_sub
    substract =n_substract
    
print(ans)
     
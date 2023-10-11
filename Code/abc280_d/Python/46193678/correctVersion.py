n=int(input())
f=[]
y=n
for i in range(2,int(y**0.5)+1):
    while n%i==0:
        f.append(i)
        n//=i
if n!=1:
    f.append(n)
if max(f)>int(y**0.5)+1:
    print(max(f))

else:
    import math
    x=2
    while True:
        y//=math.gcd(y,x)
        if y==1:
            print(x)
            exit()
        else:
            x+=1

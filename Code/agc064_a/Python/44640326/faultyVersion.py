n=int(input())
if n==3:
    s=[3,2,3,1,3,2]
elif n%2==1:
    t=[3,2,3,1,2,3]
    s=[]
    for i in range((n-5)//2,-1,-1):
        for j in range(3+i*2):
            if i==(n-5)//2:
                s.append(4+i*2)
                s.append(5+i*2)
            else:
                s.append(5+i*2)
                s.append(4+i*2)
    s+=t
    for i in range((n-2)//2):
        s.append(i*2+5)
        s.append(i*2+4)
        s.append(i*2+5)
else:
    s=[]
    for i in range((n-4)//2,-1,-1):
        for j in range(i*2+2):
            if i==0:
                s.append(i*2+3)
                s.append(i*2+4)
            else:
                s.append(i*2+4)
                s.append(i*2+3)
    for i in range(n//2):
        s.append(i*2+2)
        s.append(i*2+1)
        s.append(i*2+2)  
print(*s)
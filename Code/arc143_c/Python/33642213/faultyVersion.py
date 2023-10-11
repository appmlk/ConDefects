N,X,Y,*A=map(int,open(0).read().split());f=0;s=["Second","First"]
for a in A:
    if a%(X+Y)>=max(X,Y):f=1
    elif a%(X+Y)>=min(X,Y):print([X<Y]);exit()
print(s[f])
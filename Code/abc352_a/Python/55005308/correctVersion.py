N,X,Y,Z = map(int,input().split())

if (X <= Z and Z <= Y) or (Y <= Z and Z <= X):
    print("Yes")
else:
    print("No")
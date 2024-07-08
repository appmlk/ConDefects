A,M,L,R = map(int,input().split())
x1 = -(-(L-A)//M)
x2 = (R-A)//M
if (L-A)%M == 0:
    print(abs(x1-x2)+1)
else:
    print(abs(x1-x2))
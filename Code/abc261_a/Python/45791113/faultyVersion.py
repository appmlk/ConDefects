L1,R1,L2,R2 = map(int,input().split())
if L1 <= L2 <= R1 <= R2:
    print(R1-L2)
elif L2 <= L1 <= R2 <= R1:
    print(R2-L1)
elif L2 <= L1 <= R1 <= R2:
    print(R1-L1)
elif L1 <= L2 <= R2 <= R1:
    print(R2-L2)
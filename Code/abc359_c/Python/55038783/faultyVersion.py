Sx,Sy = map(int,input().split())
Tx,Ty = map(int,input().split())
if (Sx + Sy)%2 != 0:
    posS = "right"
    Sx -= 1
if (Tx + Ty)%2 != 0:
    Tx -= 1

if abs(Tx-Sx) > abs(Ty-Sy):
    print(abs(Tx-Sx))
else:
    print(abs(Ty-Sy))
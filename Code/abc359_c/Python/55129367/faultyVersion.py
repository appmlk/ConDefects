Sx, Sy = map(int, input().split())
Tx, Ty = map(int, input().split())

Sx -= (Sy - Sx) % 2
Tx -= (Ty - Tx) % 2


Tx -= Sx
Ty -= Sy

Tx = abs(Tx)
Ty = abs(Ty)

print(Ty + max(0, Tx - Ty) / 2)


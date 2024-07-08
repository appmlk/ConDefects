Sx, Sy = map(int, input().split())
Tx, Ty = map(int, input().split())

# S, T ともに左側基準に
if (Sx + Sy) % 2 == 1:
    Sx -= 1
if (Tx + Ty) % 2 == 1:
    Tx -= 1

# (Sx, Sy) = (0, 0) と考えたとき、(Tx, Ty)を第一第一象限に移動させる
Tx -= Sx
Ty -= Sy
Tx = abs(Tx)
Ty = abs(Ty)

Tx = max(Tx - Ty, 0) // 2
print(Tx+Ty)

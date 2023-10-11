ir = lambda: int(input()) # 数字の読み込み
lr = lambda: list(map(int, input().split())) # 数字の配列の読み込み

N, x, y = lr()
A = lr()

xA = A[2::2]
sxA = sum(xA)
xN = abs(x - A[0] - sxA)
xdp = [0] * (xN + 1)
xdp[0] = 1
for dx in xA:
    for i in range(xN-dx*2, -1, -1):
        xdp[dx*2+i] |= xdp[i]
yA = A[1::2]
syA = sum(yA)
yN = abs(y - syA)
ydp = [0] * (yN + 1)
ydp[0] = 1
for dy in yA:
    for i in range(yN-dy*2, -1, -1):
        ydp[dy*2+i] |= ydp[i]
print('Yes' if xdp[xN] and ydp[yN] else 'No')
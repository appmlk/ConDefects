D = int(input())

square = []
for i in range(10**7):
    if i**2 > D:
        break
    square.append(i**2)

ans = 10**12
x = 0
y = len(square)-1
while x < len(square):
    value = square[x] + square[y]
    ans = min(ans,abs(value-D))
    if value < D:
        x += 1
    elif value > D:
        y -= 1
    elif value == D:
        break

print(ans)
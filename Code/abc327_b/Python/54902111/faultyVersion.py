b = int(input())
ans = -1
for a in range(16):
    if a**a == b:
        ans = a
        break
print(ans)

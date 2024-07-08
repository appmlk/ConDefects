b = int(input())
ans = -1
for a in range(1, 17):
    if a**a == b:
        ans = a
        break
print(ans)

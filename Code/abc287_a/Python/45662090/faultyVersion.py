n = int(input())
result = 0
for _ in range(n):
    if "For" == input():
        result += 1

if result >= n // 2:
    print("Yes")
else:
    print("No")

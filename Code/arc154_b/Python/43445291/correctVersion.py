n = int(input())
s = input()
t = input()

if sorted(list(s)) != sorted(list(t)):
    print(-1)
    exit()

idx = n - 1
for j, i in enumerate(s[::-1]):
    while idx >= 0 and t[idx] != i:
        idx -= 1

    if idx < 0:
        print(n - j)
        exit()
    idx -= 1

print(0)

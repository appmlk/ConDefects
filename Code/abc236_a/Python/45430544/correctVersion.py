s = input()
a, b = map(int, input().split())
n = a - 1
m = b - 1
x = s[n]
y = s[m]
print(s[:n] + y + s[a:m] + x + s[m + 1 :])
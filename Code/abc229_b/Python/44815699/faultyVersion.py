import sys

a, b = input().split()

n = min(len(a), len(b))

for i in range(1, n):
    if int(a[-i])+int(b[-i]) > 9:
        print("Hard")
        sys.exit()
print("Easy")
    
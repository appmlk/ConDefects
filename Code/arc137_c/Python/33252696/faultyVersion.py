n = int(input())
a = list(map(int, input().split()))


a = sorted(a)
max_n = a[-1]
max2_n = a[-2]


if max_n - max2_n >= 2:
    print("Alice")
    exit()

empty = 1 + max_n - n
if empty & 2 == 0:
    print("Bob")
else:
    print("Alice")
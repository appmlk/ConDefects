S = input()
num = set([i for i in range(0, 10)])

for s in S:
    num.discard(int(s))

print(*num)

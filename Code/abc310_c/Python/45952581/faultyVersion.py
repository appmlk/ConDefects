N = int(input())

Z = set()

count = 0
for i in range(N):
    S = input()
    if S in Z:
        count += 1
    Z.add(S)
    Z.add(S[::-1])

print(count)

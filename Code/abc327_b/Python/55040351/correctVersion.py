b = int(input())

for i in range(1, 16):
    if i**i == b:
        print(i)
        exit()

print(-1)
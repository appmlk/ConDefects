b = int(input())

for i in range(1, 15):
    if i**i == b:
        print(i)
        exit()

print(-1)
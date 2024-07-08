B = int(input())

for i in range(20):
    if i ** i == B:
        print(i)
        exit()

print(-1)
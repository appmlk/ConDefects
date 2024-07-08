B = int(input())
for i in range(19):
    if i ** i == B:
        print(i)
        exit()
    if i ** i > B:
        break
print(-1)
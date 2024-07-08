b = int(input())

flag = False
for i in range(1, 17):
    if i**i == b:
        flag = True
        break
print(i if flag else -1)
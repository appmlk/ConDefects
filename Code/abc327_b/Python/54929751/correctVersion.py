b = int(input())
answer = -1
for i in range(1,16):
    if i**i == b:
        answer = i
    else:
        pass
print(answer)
h = int(input())

cnt = 1
plant = 0
while plant < h:
    plant = plant + 2**cnt
    cnt += 1

print(cnt)
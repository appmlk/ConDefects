n = int(input())

max = n
min = 0
past = -1
cnt = 0
ans = -1
while cnt <= 20 and max-min > 1:
    mid = (max+min)//2
    print("? " + str(mid) + '\n')
    s = int(input())
    if s == 1:
        max = mid
    else:
        min = mid
    cnt += 1

print("! " + str(min))
exit()
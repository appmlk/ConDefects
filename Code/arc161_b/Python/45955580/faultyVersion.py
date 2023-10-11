T = int(input())
for i in range(T):
    N = int(input())
    if N < 7:
        print(-1)
        continue
    tem = str(bin(N))[2:]
    res = [0 for i in range(len(tem))]
    count = 0
    flag = True
    for i in reversed(range(len(tem))):
        if tem[i] == "1":
            count += 1
    if count >= 3:
        count2 = 0
        for i in range(len(tem)):
            if count2 >= 3:
                break
            if tem[i] == "1":
                res[i] = 1
                count2 += 1
    else:
        for i in reversed(range(len(tem))):
            if tem[i] == "1":
                res[i] = 1
        count2 = 0
        for ind, i in enumerate(reversed(tem)):
            if i == "1":
                count2 += 1
                if ind >= (3-count) + count2:
                    res[len(res)-ind-1] = 0
                    for j in range((3-count) + count2):
                        res[len(res)-ind+j] = 1
                    break
    res_num = 0
    for i in range(len(res)):
        if res[i] == 0:
            continue
        res_num += 1 << (len(res)-i-1)
    print(res_num)

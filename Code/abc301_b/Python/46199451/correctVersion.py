n = int(input())
a = list(map(int, input().split()))

ans_list = []
for i in range(n - 1):
    # print(a[i + 1] - a[i])
    if a[i + 1] >= a[i]:
        ans_list.extend(i for i in range(a[i], a[i + 1]))
    elif a[i + 1] < a[i]:
        ans_list.extend(i for i in range(a[i], a[i + 1], -1))
ans_list.append(a[-1])
print(*ans_list)
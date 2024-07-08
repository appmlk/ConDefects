n,h,x = input().split()
p = input()
# print(n)
# print(h)
# print(x)
# print(p)
split_p = p.split()
list_p = list(split_p)
# print(list_p)

for i in range(0, int(n)):
    # print(list_p[i])
    if int(list_p[i]) >= int(x) - int(h):
        print(i+1)
        break
    else:
        continue

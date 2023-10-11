user_n, q = map(int, input().split(" "))
dic = {}

for i in range(q):
    ope, user1, user2 = map(int, input().split(" "))
    if user1 not in dic:
        dic[user1] = set()
    if user2 not in dic:
        dic[user2] = set()
    if ope == 1:
        dic[user1].add(user2)
    elif ope == 2:
        if user2 in dic[user1]:
            dic[user1].remove(user2)
    else:
        if user1 in dic[user2] and user2 in dic[user1]:
            print("Yes")
        else:
            print("No")

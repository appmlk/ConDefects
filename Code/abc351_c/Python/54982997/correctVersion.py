from collections import deque

n = int(input())
input_list = [int(x) for x in input().split(' ')]


tfe = deque()

temp = None
for x in input_list:
    temp = x
    if tfe and tfe[-1] == temp:
        while tfe and tfe[-1] == temp:
            temp += 1
            tfe.pop()
        tfe.append(temp)
    else:
        tfe.append(temp)
print(len(tfe))
    
n=int(input())
a=[]
b=['H', 'D', 'C', 'S']
first = set(b)
second = set(['A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'])
tmp = set()
answer = "Yes"
for i in range(n):
    c=input()
    if (c[0] not in first or c[1] not in second or c in tmp):
        answer = "No"

    tmp.add(c)

print(answer)
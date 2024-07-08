S = input()
lst, element, rest, cnt_lst = [], [], [], []
now = ""

for a in S:
    lst.append(a)
lst = sorted(lst)

for a in lst:
    if now != a:
        element.append(a)
    now = a

for m in element:
    rest.append(lst.count(m))

for n in range(len(S)+1):
    cnt_element = rest.count(n)
    if cnt_element == 0 or cnt_element == 2:
        continue
    else:
        print("No")
        exit()
    
print("Yes")
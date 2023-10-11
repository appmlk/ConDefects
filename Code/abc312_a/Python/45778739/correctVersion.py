S = input()
curr = ["ACE", "BDF", "CEG", "DFA", "EGB", "FAC", "GBD"]
ans = "No"
for c in curr:
    if S == c:
        ans = "Yes"
        break
print(ans)
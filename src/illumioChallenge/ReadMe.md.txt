### ReadMe.md
Submission by : Ramya Sarma (ramyasarma13@gmail.com)

## Solution design

I created a class in java (fireWall.java). 
I used the Buffered Reader library to parse the CSV file containing the rules. Then I used simple string functions to process the rules and populate the dictionary. Next I store the rules in the form of a dictionary which has the following structure. 

{ K1 : {
		{ K2 : K3 }
	}
}

where K1 is the set of valid IP addresses concatenated with the port numbers,
K2 is the direction.
K3 is the protocol.

## Time complexity analysis

Since I use preprocessed the rules,

LookUp/ Query - O(1) 
Insertion of new query - O(n) where n is the number of valid queries possible allowed by the rule.

I ran my solutions against the 4 example rules and found that it takes () to run. This is not ideal solution for real time systems and needs a lot of optimization.

## Space complexity analysis

Since I store all the set of valid rules in the form of a dictionary, I need O(n) space where n is the number of IP addresses possible.

## System assumptions 
I have assumed that the number of queries/ read requests is expected to be higher than updates.
In other words, I assume that the frequency of updates to the rules is expected to be lesser than the number of queries.

## Possible alternate solutions that I would have liked to explore

1. Use regex matching for the existing rules to improve the space complexity. 
We can use regex based conditions to validate incoming requests. This way, if the number of rules is large for eg. 400k queries, then I just need to check against each rule and see which rule the query satisfies, if any.

2. We could explore the use of alternate data structure to simplify query lookup and reduce storage.

3. We could use a database type server to process the set of rules. This way we can index the set of rules to make search faster. 

### Testing 

### Teams Preference
1. Data
2. Platform
3. Policy
 
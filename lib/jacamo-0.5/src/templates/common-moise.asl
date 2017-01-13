/* auxiliary plans for Moise agents */

// keep focused on schemes that my groups are responsible for
@l_focus_on_my_scheme[atomic]
+schemes(L)[workspace(_,_,W)] 
   <- cartago.set_current_wsp(W);
      for ( .member(S,L) ) {
         lookupArtifact(S,ArtId);
         focus(ArtId)
      }.

// the goal !focus_org_art is produced by the JaCaMo launcher for the agent to focus on initial artifacts
+!jcm__initial_roles([],_).
+!jcm__initial_roles(L,0) <- .print("Error with initial role ",L).

@l_focus_org_art[atomic]
+!jcm__initial_roles([H|T],Try)
   <- !jcm__initial_roles(H,Try);
      !jcm__initial_roles(T,Try).
+!jcm__initial_roles(role(O,H,G,R),Try)
   <- !join_workspace(O,H); 
      lookupArtifact(G,GId);
      +jcm__art(O,G,GId);
      focus(GId);
      adoptRole(R)[artifact_id(GId)];
      .print("playing ",R," in ",O,".",G).
-!jcm__initial_roles(L,Try)
   <- //.print("wait a bit to focus on ",L);
      .wait(100);
      !jcm__initial_roles(L,Try-1).
